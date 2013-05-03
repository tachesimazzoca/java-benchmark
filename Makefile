.PHONY: clean
clean:
	@rm -rf target

.PHONY: checkstyle
checkstyle:
	@mvn checkstyle:checkstyle

.PHONY: compile
compile:
	@mvn compile

.PHONY: package
package:
	@mvn package

.PHONY: javadoc
javadoc:
	@mvn javadoc:javadoc

.PHONY: site
site:
	@mvn site

