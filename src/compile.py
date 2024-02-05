import os
import glob

def copy_java_files_to_txt(root_dir, output_file):
    with open(output_file, 'w') as output:
        for java_file in glob.iglob(root_dir + '/**/*.java', recursive=True):
            with open(java_file, 'r') as java:
                output.write(java.read())
                output.write('\n')  # Adding a newline between each file

# Specify the root directory where your Java files are located
root_directory = '/Users/dylansiew/Desktop/CS2113-Indiv-Proj---NUS'
# Specify the output text file
output_file_path = '/Users/dylansiew/Desktop/CS2113-Indiv-Proj---NUS/output.txt'

copy_java_files_to_txt(root_directory, output_file_path)
