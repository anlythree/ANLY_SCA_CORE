# anly-sca-core

a custom springbootstarter，
It contains a lot of related framework poms, don‘t need developers to import all of poms,  
Contains dependencies and basic configurations for dubbo、mysql、redis、es、kafka、springcloudalibaba
only needs to import this starter to start working directly：
<parent>
		<groupId>com.anlythree</groupId>
		<artifactId>anly-sca-core</artifactId>
		<version>0.0.1-release</version>
	</parent>
Notice！！！ if your environment is JDK 17 and you need to use Spring Boot 3, you need to import the following dependencies:
 <parent>
		<groupId>com.anlythree</groupId>
		<artifactId>anly-sca-core</artifactId>
		<version>0.0.1-jdk17</version>
	</parent>

if you have anly question，you can create a issues or send email to 15610462126@163.com
🍻
