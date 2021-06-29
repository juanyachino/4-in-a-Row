#/bin/bash 

#set -x

cd src/

oldtext="Base.open()"
newtext='Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/4inaRow_test", "root", "")'

classes=$(grep -rl "Base.open()" .)

for c in $classes
do
    sed -i "s%$oldtext%$newtext%g" $c
done
