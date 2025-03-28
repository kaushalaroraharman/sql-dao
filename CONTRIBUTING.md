# How to contribute

Support and contributions from the open source community are essential for keeping
`eclipse-ecsp/sql-dao` up to date and always improving! There are a few guidelines that we need
contributors to follow to keep the project consistent, as well as allow us to keep
maintaining `eclipse-ecsp/sql-dao` in a reasonable amount of time.

Please note that this project is released with a [Contributor Code of Conduct][coc].

By participating in this project you agree to abide by its terms.

[coc]: ./CODE_OF_CONDUCT.template

## Creating an Issue

Before you create a new Issue:

1. Please make sure there is no [open issue](https://github.com/eclipse-ecsp/sql-dao/issues) yet.
2. If it is a bug report, include the steps to reproduce the issue and please create a reproducible test case.
3. If it is a feature request, please share the motivation for the new feature and how you would implement it.
4. Please include links to the corresponding GitHub documentation.

## Coding style check configuration

[checkstyle.xml](./checkstyle.xml) is the HARMAN coding standard to follow while writing new/updating existing code.

Checkstyle plugin [maven-checkstyle-plugin:3.2.1](https://maven.apache.org/plugins/maven-checkstyle-plugin/) 
is integrated in [pom.xml](./pom.xml) which runs in the `validate` phase and `check` goal 
of the maven lifecycle and fails the build if there are any checkstyle errors in the project.

## Tests

If you want to submit a bug fix or new feature, make sure that all tests are passing.

```mvn clean test```

Or run a specific module tests: (example module-1)

```
mvn clean test -pl :module-1
```

## Making Changes

- Create a topic branch from the main branch.
- Check for unnecessary whitespace / changes with `git diff --check` before committing.
- Keep git commit messages clear and appropriate. Ideally follow commit conventions described below.

## Submitting the Pull Request

- Push your changes to your topic branch on your fork of the repo.
- Submit a pull request from your topic branch to the [main](https://github.com/eclipse-ecsp/sql-dao) branch on the `eclipse-ecsp/sql-dao` repository.
- Be sure to tag any issues your pull request is taking care of / contributing to. \* Adding "Closes #123"
to a pull request description will auto close the issue once the pull request is merged in.


## Merging a PR and Shipping a release (maintainers only)

- A PR can only be merged into main branch by a maintainer if: CI is passing, approved by another maintainer and is up-to-date with the default branch.
- Ensure that the PR is tagged with related [issue](https://github.com/eclipse-ecsp/sql-dao/issues) it intends to resolve.
- Change log for all the PRs merged since the last release should be included in the release notes.
- Automatically generated release notes is configured for the repo and must be used while creating a new release tag.
