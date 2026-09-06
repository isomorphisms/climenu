# `gh` example corpus

`gh` is the first substantial example program for `climenu`.

The checked-in compact fixture is pinned to GitHub CLI 2.100.0. `GhExample.kt` supplies the visible root-command menu used by the Android example, and `command_tree.tsv` records both visible commands and several hidden/special root commands found in the source command tree.

## Manual and long-form material

Authoritative sources are indexed in `reading.tsv`:

- GitHub CLI manual and root `gh` manual page
- GitHub CLI usage examples
- GitHub's CLI documentation and quickstart
- the `cli/cli` source tree
- its project-layout and command-line-syntax documents
- Cobra, the parser/command framework used by `gh`

I did not find a dedicated official book specifically about the `gh` executable. The official manual plus GitHub CLI documentation are therefore treated as the book-like reference corpus.

The complete upstream prose is not duplicated by hand here. Run the top-level importer to materialize the full help/reference corpus, generated manpage tree, parser sources, and provenance in this repository:

```sh
sh ./import-gh
```

By default that creates:

```text
examples/gh/imported/v2.100.0/
    GH_VERSION.txt
    LICENSE
    PROVENANCE.txt
    UPSTREAM_COMMIT.txt
    help/
        gh.txt
        reference.txt
    man1/
        gh.1
        gh-*.1
    parser/
        root.go
        go.mod
        go.sum
        docs/
        pkg/cmd/
        pkg/cmdutil/
```

`help/reference.txt` is useful as a single long-form command corpus. `man1/` is the generated Unix manual tree when the needed Go toolchain is available.

## Where `gh` parses arguments

There is no single `gh` parser file. The root command is assembled by:

```text
pkg/cmd/root/root.go
    NewCmdRoot(...)
        cobra.Command
        PersistentFlags()
        Flags()
        AddCommand(...)
```

The command implementations under `pkg/cmd/` construct their own Cobra commands and declare their local arguments and flags. Cobra delegates POSIX-style flag parsing to `pflag`.

For the pinned release, `go.mod` declares Cobra 1.10.2 and pflag 1.0.10. The importer therefore retains `pkg/cmd`, `pkg/cmdutil`, `root.go`, and the Go dependency manifest rather than pretending the parser is one small source file.

## Provenance

Upstream: `https://github.com/cli/cli`

Pinned release: `v2.100.0`

License: MIT; the importer copies the upstream license beside the imported corpus.
