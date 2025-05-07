# Purchase Checker
Purchase Checker is a verifier system for customers to verify their account to get a special
role in our Discord server and get access to source code of the products purchased.

## Contributing
I accept Pull Requests via GitHub. There are some guidelines which will make applying PRs easier for me:
+ Ensure you didn't use spaces! Please use tabs for indentation.
+ Respect the code style.
+ Do not increase the version numbers in any examples files and the README.md to the new version that this Pull Request would represent.
+ Create minimal diffs - disable on save actions like reformat source code or organize imports. If you feel the source code should be reformatted create a separate PR for this change.

You can learn more about contributing via GitHub in [contribution guidelines](../CONTRIBUTING.md).

## License
This code is under the [GPL-3.0 License](http://www.gnu.org/licenses/gpl-3.0.html).

See the [LICENSE](../LICENSE) file for required notices and attributions.

## Building from source
To build this project from source code, run the following from Git Bash:
```
git clone https://www.github.com/Despical/PurchaseChecker && cd PurchaseChecker
mvn clean package -DskipTests
```

> **[Maven](https://maven.apache.org/)** must be installed to build this project.
