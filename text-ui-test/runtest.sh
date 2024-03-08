#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]; then
    mkdir ../bin
fi

# delete output from previous run
if [ -f "ACTUAL.TXT" ]; then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src/main/java \
    -Xlint:none \
    -d ../bin \
    ../src/main/java/alpaca/*.java \
    ../src/main/java/alpaca/commands/*.java \
    ../src/main/java/alpaca/exceptions/*.java \
    ../src/main/java/alpaca/parser/*.java \
    ../src/main/java/alpaca/storage/*.java \
    ../src/main/java/alpaca/tasks/*.java \
    ../src/main/java/alpaca/ui/*.java; then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to ACTUAL.TXT
java -classpath ../bin alpaca.Alpaca < input.txt > ACTUAL.TXT

# compare the output to the expected output using 'diff'
# '-Z' ignores trailing whitespace, '-q' provides brief output
diff -Zq ACTUAL.TXT EXPECTED.TXT > /dev/null
result=$?

if [ $result -eq 0 ]; then
    echo "Test result: PASSED"
else
    echo "Test result: FAILED"
    diff -Zy --suppress-common-lines ACTUAL.TXT EXPECTED.TXT
fi

# Exit with the result of comparison
exit $result
