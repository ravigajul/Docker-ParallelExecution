#!/bin/bash
Build_pid=$(sudo netstat -tpln|grep :8000|awk -F ' ' '{print $7}'|cut -d '/' -f1)
echo "Processing the Build"
sudo systemctl start docker
sleep 5
cd /automation/QASanityAutomation/UISanityHealthCheck-Linear
sudo docker-compose down
sudo docker-compose up -d --scale chrome=2
sleep 10
sudo mvn test
sudo docker-compose down
# cd /automation/QASanityAutomation/UISanityHealthCheck/
# sudo python -m SimpleHTTPServer 8000 2>&1 /dev/null
sudo systemctl stop docker