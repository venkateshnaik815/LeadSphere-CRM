// @ts-nocheck
import {
  throwError as _throw,
  connectable,
  defer,
  fromEvent,
  merge,
  Observable,
  Observer,
  ReplaySubject,
  Subject,
} from 'rxjs';
import { distinctUntilChanged, map, mergeMap, take } from 'rxjs/operators';
import { IncomingResponseDeserializer } from '../deserializers/incoming-response.deserializer.js';
import { InvalidMessageException } from '../errors/invalid-message.exception.js';
import {
  ClientOptions,
  KafkaOptions,
  MqttOptions,
  MsPattern,
  NatsOptions,
  PacketId,
  ReadPacket,
  RedisOptions,
  RmqOptions,
  TcpClientOptions,
  WritePacket,
} from '../interfaces/index.js';
import { ProducerDeserializer } from '../interfaces/deserializer.interface.js';
import { ProducerSerializer } from '../interfaces/serializer.interface.js';
import { IdentitySerializer } from '../serializers/identity.serializer.js';
import { transformPatternToRoute } from '../utils/index.js';
import { randomStringGenerator, isNil } from '@nestjs/common/internal';

export abstract class ClientProxy<
  EventsMap extends Record<never, Function> = Record<never, Function>,
  Status extends string = string,
> {
  protected routingMap = new Map<string, Function>();
  protected serializer: ProducerSerializer;
  protected deserializer: ProducerDeserializer;
  protected _status$ = new ReplaySubject<Status>(1);

  public get status(): Observable<Status> {
    return this._status$.asObservable().pipe(distinctUntilChanged());
  }

  public abstract connect(): Promise<any>;
  public abstract close(): any;
  public on<
    EventKey extends keyof EventsMap = keyof EventsMap,
    EventCallback extends EventsMap[EventKey] = EventsMap[EventKey],
  >(event: EventKey, callback: EventCallback) {
    throw new Error('Method not implemented.');
  }
  public abstract unwrap<T>(): T;

  public send<TResult = any, TInput = any>(
    pattern: any,
    data: TInput,
  ): Observable<TResult> {
    if (isNil(pattern) || isNil(data)) {
      return _throw(() => new InvalidMessageException());
    }
    return defer(async () => this.connect()).pipe(
      mergeMap(
        () =>
          new Observable((observer: Observer<TResult>) => {
            const callback = this.createObserver(observer);
            return this.publish({ pattern, data }, callback);
          }),
      ),
    );
  }

  public emit<TResult = any, TInput = any>(
    pattern: any,
    data: TInput,
  ): Observable<TResult> {
    if (isNil(pattern) || isNil(data)) {
      return _throw(() => new InvalidMessageException());
    }
    const source = defer(async () => this.connect()).pipe(
      mergeMap(() => this.dispatchEvent({ pattern, data })),
    );
    const connectableSource = connectable(source, {
      connector: () => new Subject(),
      resetOnDisconnect: false,
    });
    connectableSource.connect();
    return connectableSource;
  }

  protected abstract publish(
    packet: ReadPacket,
    callback: (packet: WritePacket) => void,
  ): () => void;

  protected abstract dispatchEvent<T = any>(packet: ReadPacket): Promise<T>;

  protected createObserver<T>(
    observer: Observer<T>,
  ): (packet: WritePacket) => void {
    return ({ err, response, isDisposed }: WritePacket) => {
      if (err) {
        return observer.error(this.serializeError(err));
      } else if (response !== undefined && isDisposed) {
        observer.next(this.serializeResponse(response));
        return observer.complete();
      } else if (isDisposed) {
        return observer.complete();
      }
      observer.next(this.serializeResponse(response));
    };
  }

  protected serializeError(err: any): any {
    return err;
  }

  protected serializeResponse(response: any): any {
    return response;
  }

  protected assignPacketId(packet: ReadPacket): ReadPacket & PacketId {
    const id = randomStringGenerator();
    return Object.assign(packet, { id });
  }

  protected connect$(
    instance: any,
    errorEvent = 'error',
    connectEvent = 'connect',
  ): Observable<any> {
    const error$ = fromEvent(instance, errorEvent).pipe(
      map((err: any) => {
        throw err;
      }),
    );
    const connect$ = fromEvent(instance, connectEvent);
    return merge(error$, connect$).pipe(take(1));
  }

  protected getOptionsProp<
    Options extends ClientOptions['options'],
    Attribute extends keyof Options,
  >(obj: Options, prop: Attribute): Options[Attribute];
  protected getOptionsProp<
    Options extends ClientOptions['options'],
    Attribute extends keyof Options,
    DefaultValue extends Options[Attribute] = Options[Attribute],
  >(
    obj: Options,
    prop: Attribute,
    defaultValue: DefaultValue,
  ): Required<Options>[Attribute];
  protected getOptionsProp<
    Options extends ClientOptions['options'],
    Attribute extends keyof Options,
    DefaultValue extends Options[Attribute] = Options[Attribute],
  >(
    obj: Options,
    prop: Attribute,
    defaultValue: DefaultValue = undefined as DefaultValue,
  ) {
    return obj && prop in obj ? (obj as any)[prop] : defaultValue;
  }

  protected normalizePattern(pattern: MsPattern): string {
    return transformPatternToRoute(pattern);
  }

  protected initializeSerializer(options: ClientOptions['options']) {
    this.serializer =
      (options &&
        (options as
          | RedisOptions['options']
          | NatsOptions['options']
          | MqttOptions['options']
          | TcpClientOptions['options']
          | RmqOptions['options']
          | KafkaOptions['options'])!.serializer) ||
      new IdentitySerializer();
  }

  protected initializeDeserializer(options: ClientOptions['options']) {
    this.deserializer =
      (options &&
        (options as
          | RedisOptions['options']
          | NatsOptions['options']
          | MqttOptions['options']
          | TcpClientOptions['options']
          | RmqOptions['options']
          | KafkaOptions['options'])!.deserializer) ||
      new IncomingResponseDeserializer();
  }
}
