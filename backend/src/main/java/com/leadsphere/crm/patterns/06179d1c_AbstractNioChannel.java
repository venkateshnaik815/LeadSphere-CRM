package com.leadsphere.crm.patterns;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import lombok.Getter;

public abstract class AbstractNioChannel {

  private final SelectableChannel channel;
  @Getter private final ChannelHandler handler;
  private final Map<SelectableChannel, Queue<Object>> channelToPendingWrites;
  private NioReactor reactor;

  public AbstractNioChannel(ChannelHandler handler, SelectableChannel channel) {
    this.handler = handler;
    this.channel = channel;
    this.channelToPendingWrites = new ConcurrentHashMap<>();
  }

  void setReactor(NioReactor reactor) {
    this.reactor = reactor;
  }

  public SelectableChannel getJavaChannel() {
    return channel;
  }

  public abstract int getInterestedOps();

  public abstract void bind() throws IOException;

  public abstract Object read(SelectionKey key) throws IOException;

  void flush(SelectionKey key) throws IOException {
    var pendingWrites = channelToPendingWrites.get(key.channel());
    Object pendingWrite;
    while ((pendingWrite = pendingWrites.poll()) != null) {
      // ask the concrete channel to make sense of data and write it to java channel
      doWrite(pendingWrite, key);
    }
    // We don't have anything more to write so channel is interested in reading more data
    reactor.changeOps(key, SelectionKey.OP_READ);
  }

  protected abstract void doWrite(Object pendingWrite, SelectionKey key) throws IOException;

  public void write(Object data, SelectionKey key) {
    var pendingWrites = this.channelToPendingWrites.get(key.channel());
    if (pendingWrites == null) {
      synchronized (this.channelToPendingWrites) {
        pendingWrites =
            this.channelToPendingWrites.computeIfAbsent(
                key.channel(), k -> new ConcurrentLinkedQueue<>());
      }
    }
    pendingWrites.add(data);
    reactor.changeOps(key, SelectionKey.OP_WRITE);
  }
}
