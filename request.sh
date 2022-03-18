#!/bin/bash
IP_ADDRESS=$(docker inspect -f '{{range.NetworkSettings.Networks}}{{.IPAddress}}{{end}}' csv-reader)

echo
echo "************************************************* "
echo curl -v 'http://'"$IP_ADDRESS"':8080/api/csv/upload' \
     -H "Content-Type: multipart/form-data" \
     -F 'file=@./src/main/resources/input.csv'
echo "************************************************* "
echo

curl -v 'http://'"$IP_ADDRESS"':8080/api/csv/upload' \
-H "Content-Type: multipart/form-data" \
-F 'file=@./src/main/resources/input.csv'

echo
echo
echo
echo "************************************************* "
echo curl -i -X GET -H "Content-Type: application/json" \
'http://'"$IP_ADDRESS"':8080/api/folders'
echo "************************************************* "
echo

curl -i -X GET -H "Content-Type: application/json" \
'http://'"$IP_ADDRESS"':8080/api/folders'