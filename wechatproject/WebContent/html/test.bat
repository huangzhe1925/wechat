@echo off
echo start
echo startfdsfdada


set /a hour=%time:~0,2%+0
set /a minu=%time:~3,2%+0
set /a sec=%time:~6,2%+0
echo %hour%:%minu%:%sec% 
set /a minu=minu+1
if %sec% GEQ 60 (
	set /a sec=%sec%-60
	set /a minu=%minu%+1 
)

if %minu% GEQ 60 (
	set /a minu=%minu%-60 
	set /a hour=%hour%+1 
)

if %hour% EQU 24 (
	set hour=00
)

if %minu% LEQ 9 (
	set minu=0%minu%
)

if %sec% LEQ 9 (
	set sec=0%sec%
)

set afterTime=%hour%:%minu%:%sec%

set COMMAND=cmd.exe /k C:\work\test1.bat
echo %afterTime%
::set /a minu=%time:~3,2%+5
::set hour=%time:~0,2%
::set FileTime=%time:~0,3%%minute%
::echo %FileTime% 
schtasks /create /tn "task" /tr "%COMMAND%" /sc ONCE /st %afterTime% /F
