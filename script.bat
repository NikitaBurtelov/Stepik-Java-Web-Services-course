@echo off
chcp 65001
echo Порты
REM netsh advfirewall firewall add rule dir=in action=block protocol=tcp localport=8079-8081 name=»TCP
REM netstat -an |find /i "listening"
netstat -an | more
pause