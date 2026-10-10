#!/usr/bin/env sh

#  Licensed to the Apache Software Foundation (ASF) under one
#  or more contributor license agreements.  See the NOTICE file
#  distributed with this work for additional information
#  regarding copyright ownership.

case "`uname`" in
  CYGWIN* ) cygwin=true ;;
  MINGW* ) mingw=true ;;
  MSYS* ) msys=true ;;
esac

PRG="\$0"
while [ -h "\$PRG" ] ; do
  ls=`ls -ld "$PRG"`
  link=`expr "$ls" : '.*-> \(.*\)$'`
  if expr "\$link" : '/.*' > /dev/null; then
    PRG="\$link"
  else
    PRG=`dirname "$PRG"`/"\$link"
  fi
done

SAVED="`pwd`"
cd "`dirname \"$PRG\"`/" >/dev/null
APP_HOME="`pwd`"
cd "\$SAVED" >/dev/null

APP_NAME="Gradle"
APP_BASE_NAME=`basename "$0"`

DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

if [ -n "\$JAVA_HOME" ] ; then
    if [ -x "\$JAVA_HOME/jre/sh/java" ] ; then
        JAVACMD="\$JAVA_HOME/jre/sh/java"
    else
        JAVACMD="\$JAVA_HOME/bin/java"
    fi
else
    JAVACMD="java"
fi

if [ ! -x "\$JAVACMD" ] ; then
    echo "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH." >&2
    exit 1
fi

if [ -z "\$GRADLE_USER_HOME" ]; then
    GRADLE_USER_HOME=\$HOME/.gradle
fi

CLASSPATH=\$APP_HOME/gradle/wrapper/gradle-wrapper.jar

exec "\$JAVACMD" \(DEFAULT_JVM_OPTS\)JAVA_OPTS \(GRADLE_OPTS "-Dorg.gradle.appname=\)APP_BASE_NAME" -classpath "CLASSPATH" org.gradle.wrapper.GradleWrapperMain "@"
