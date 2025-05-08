<h1 align="center">Purchase Checker</h1>

<div align="center">

[![](https://github.com/Despical/PurchaseChecker/actions/workflows/build.yml/badge.svg)](https://github.com/Despical/PurchaseChecker/actions/workflows/build.yml)
[![](https://img.shields.io/github/v/release/Despical/PurchaseChecker)](https://github.com/Despical/PurchaseChecker/releases/latest)
[![](https://jitpack.io/v/Despical/PurchaseChecker.svg)](https://jitpack.io/#Despical/PurchaseChecker)
[![](https://img.shields.io/badge/License-GPLv3-blue.svg)](../LICENSE)
[![](https://img.shields.io/badge/javadoc-latest-lime.svg)](https://javadoc.jitpack.io/com/github/Despical/PurchaseChecker/latest/javadoc/index.html)

Purchase Checker is a verification service for customers to verify their accounts to get a special role and get access to the
source of the products purchased.

</div>

## Contributing

I accept Pull Requests via GitHub. There are some guidelines which will make applying PRs easier for me:
+ Ensure you didn't use tabs! Please use spaces for indentation.
+ Respect the code style.
+ Do not increase the version numbers in any examples files and the README.md to the new version that this Pull Request would represent.
+ Create minimal diffs - disable on save actions like reformat source code or organize imports. If you feel the source code should be reformatted create a separate PR for this change.

You can learn more about contributing via GitHub in [contribution guidelines](CONTRIBUTING.md).

## License
This code is under the [GPL-3.0 License](http://www.gnu.org/licenses/gpl-3.0.html).

See the [LICENSE](../LICENSE) file for required notices and attributions.

## Building from source
Before building the project do not forget to change the environment variables.<br>
To build this project from source code, run the following from Git Bash:
```
git clone https://www.github.com/Despical/PurchaseChecker && cd PurchaseChecker
mvn clean package -DskipTests -Dmaven.javadoc.skip=true
```

## To run on a local host
* Copy the ``.env.example`` file and rename it to ``.evv`` then update the variables. 
* For the Flyway plugin to be able to work you can manually change the variables in the ``pom.xml`` 
under the **flyway-maven-plugin** configuration or can create global variables in the ``.m2/settings.xml`` file in your machine.
  * ``mvn flyway:migrate`` command can be run to manually generate the database and the tables.
  * By default ``jpa.hibernate.ddl-auto`` option is set to ``create-drop`` which will drop and recreate the tables on each session.
* A Discord bot must be configured with the required permissions for some endpoints to work properly.
* After starting the application, it will be running on the ``localhost:8080``.

> [!IMPORTANT]  
> **[Maven](https://maven.apache.org/)** must be installed to build this project.
