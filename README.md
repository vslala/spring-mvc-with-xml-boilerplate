Troubleshooting
--

Error: `org.springframework.beans.factory.parsing.BeanDefinitionParsingException: Configuration problem: Unable to locate Spring NamespaceHandler for XML schema namespace [http://www.springframework.org/schema/batch]`

Solution: add below dependencies to `build.gradle`
```
compile group: 'org.springframework.batch', name: 'spring-batch-infrastructure', version: '2.1.8.RELEASE'
compile group: 'org.springframework.batch', name: 'spring-batch-core', version: '2.1.8.RELEASE'
```

Error: `cvc-complex-type.3.2.2: Attribute 'local' is not allowed to appear in element 'beans:ref'`

Solution: Navigate to the file. Search for keyword `local` and replace it with `bean`.