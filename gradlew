#!/usr/bin/env sh

# ##############################################################################
#
#  Gradle startup script for POSIX systems
#
# ##############################################################################

# Attempt to set APP_HOME
# Resolve links: \$0 may be a link
PRG="\$0"
# Need this for relative symlinks.
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

# Use the maximum available, or set custom options
DEFAULT_JVM_OPTS='"-Xmx64m" "-Xms64m"'

# Collect all arguments for the java command except the name of this script
   
# Determine the Java command to use to start the JVM.
if [ -n "\$JAVA_HOME" ] ; then
    if [ -x "\$JAVA_HOME/jre/sh/java" ] ; then
        # IBM's JDK on AIX uses strange locations for the executables
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

# Locate the wrapper jar file
CLASSPATH=\$APP_HOME/gradle/wrapper/gradle-wrapper.jar

# Execute Gradle
exec "\$JAVACMD" \(DEFAULT_JVM_OPTS\)JAVA_OPTS \(GRADLE_OPTS "-Dorg.gradle.appname=\)APP_BASE_NAME" -classpath "CLASSPATH" org.gradle.wrapper.GradleWrapperMain "@"
