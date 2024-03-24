#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "/Users/yongyong/ip/bin" ]
then
    mkdir /Users/yongyong/ip/bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp /Users/yongyong/ip/src/main/java -Xlint:none -d /Users/yongyong/ip/bin /Users/yongyong/ip/src/main/java/Phoebe.java
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath /Users/yongyong/ip/bin Phoebe < input.txt > ACTUAL.TXT /Users/yongyong/ip/src/main/java/Phoebe.java

# convert to UNIX format
#cp EXPECTED.TXT EXPECTED-UNIX.TXT
#dos2unix ACTUAL.TXT EXPECTED-UNIX.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi