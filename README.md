# climenu

A command-line interface should be inspectable as an interface, not memorized as folklore.

`climenu` turns CLI metadata into one browsable GUI: flags together, descriptions beside them, examples beside those, and provenance retained so information from `--help`, man pages, completion files, or source code can eventually be merged instead of making the user visit four different representations.

## First vertical slice

The first implementation is `Climenu.idric`.

It accepts ordinary help text on stdin and emits one self-contained HTML page on stdout:

```sh
grep --help | climenu grep > grep.html
```

The page currently provides:

- every option line the help parser recognizes;
- the option's documented spelling and description;
- an automatically constructed example using the long spelling when one exists;
- a source badge (`--help` in this first adapter);
- filtering across all options;
- one-click copying of examples.

The stdin boundary is intentional. Capturing a program's help output and understanding it are different jobs, and keeping them separate preserves the normal Unix pipe interface. Automatic invocation can be added without coupling the parser or GUI to process-launching code.

## Internal model

The GUI does not parse help text. It renders a normalized command description:

```text
Command
  executable
  summary
  flags

Flag
  syntax
  description
  examples
  origins
```

An `origin` records where a fact came from. The model already names four sources:

```text
--help
man page
completion specification
argument-parser/source metadata
```

That is the important boundary: new discovery mechanisms enrich the same command description instead of becoming new user interfaces.

## Discovery order

No single discovery method works for every program. The intended ladder is:

1. structured metadata deliberately exposed by the program, when available;
2. argument-parser/source introspection;
3. shell-completion specifications;
4. man pages;
5. `--help` text as the universal-ish fallback.

These are evidence sources, not mutually exclusive modes. When several exist, `climenu` should merge them and retain provenance. A man page may have a better explanation; parser metadata may know that a value is an enum; completion data may know legal values; source examples may show realistic invocations.

## Near-term work

The next useful slices are:

- preserve wrapped/multiline help descriptions;
- identify option arguments separately from aliases;
- represent subcommands as a command tree;
- add a man-page adapter and merge it with `--help` evidence;
- add completion adapters;
- add parser-specific adapters where the parser exposes substantially better structure;
- turn option value metadata into real GUI controls rather than only documentation cards.

The generated HTML is only the first renderer. The normalized command description is the durable part.
