package org.isomorphisms.climenu

const val gh_fixture_version = "2.100.0"
const val gh_prompt = "GitHub CLI (gh)"

val gh_root_commands = listOf(
    // Core commands
    "gh auth",
    "gh browse",
    "gh codespace",
    "gh discussion",
    "gh gist",
    "gh issue",
    "gh org",
    "gh pr",
    "gh project",
    "gh release",
    "gh repo",
    "gh skill",

    // GitHub Actions commands
    "gh cache",
    "gh run",
    "gh workflow",

    // Additional commands
    "gh agent-task",
    "gh alias",
    "gh api",
    "gh attestation",
    "gh completion",
    "gh config",
    "gh copilot",
    "gh extension",
    "gh gpg-key",
    "gh label",
    "gh licenses",
    "gh preview",
    "gh ruleset",
    "gh search",
    "gh secret",
    "gh ssh-key",
    "gh status",
    "gh variable",
)
