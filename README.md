# Simple Systemd service library for Java

## Service startup problem

If you run `systemctl start youservice.service`, systemd **doesn't wait** until your service is actually started. This can cause problems if your application have slow initialization process. In this case systemd considers your application as started, but actually starting process is in progress.

## Solution

This library allows you to notify systemd system about your application is ready/started and systemd will wait until it happen. It uses linux systemd-notify tool to do it.

The library is lightweight and **doesn't have any dependencies** on logging, JNA, JNI native code. **It is pure Java solution**.

## Usage
Two steps are required.
### Step 1: edit your service unit
Add following lines to your service unit file.

```
[Service]
Type=notify
NotifyAccess=all
```

You can also configure TimeoutStartSec. Default value is 90s.

### Step 2: call library when your app is started

Add maven dependency to your `pom.xml`.
```xml
<dependency>
    <groupId>com.pixonic</groupId>
    <artifactId>simple-systemd-service</artifactId>
    <version>0.2</version>
</dependency>
```

And call it when your app is ready.

```java
public static void main(String[] args) {
    // ... your slow initialization code goes here ...
    
    //notify systemd about we are ready
    Systemd.sendReady();
}
```

## Build
Java 8 is required.

`mvn package`

## Known issues

This library doesn't support Spring Boot.

You should run your app as **root** because of a bug in systemd.
