// @ts-nocheck
import type { REPLServer } from 'repl';

function listAllCommands(replServer: REPLServer) {
  Object.keys(replServer.commands)
    .sort()
    .forEach(name => {
      const cmd = replServer.commands[name];
      if (cmd) {
        replServer.output.write(`${name}\t${cmd.help || ''}\n`);
      }
    });
}

export function defineDefaultCommandsOnRepl(replServer: REPLServer): void {
  replServer.defineCommand('help', {
    help: 'Show REPL options',
    action(name?: string) {
      this.clearBufferedCommand();

      if (name) {
        // Considering native commands before native nestjs injected functions.
        const nativeCommandOrFunction =
          this.commands[name] || this.context[name];
        // NOTE: If the command was retrieve from the context, it will have a `help`
        // getter property that outputs the helper message and returns undefined.
        // But if the command was retrieve from the `commands` object, it will
        // have a `help` property that returns the helper message.
        const helpMessage = nativeCommandOrFunction?.help;
        if (helpMessage) {
          this.output.write(`${helpMessage}\n`);
        }
      } else {
        listAllCommands(this);
        this.output.write('\n\n');
        this.context.help();
        this.output.write(
          '\nPress Ctrl+C to abort current expression, Ctrl+D to exit the REPL\n',
        );
      }

      this.displayPrompt();
    },
  });
}
