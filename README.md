# uitbarcodegenerator
Unique Innovation Technology Co. Barcode Generator Tool


About
============================
Unique Innovation Technology Co. Barcode Generator Tool

It will then generate a serial number with following format according to new Java 8 Date Format
```
<SERIES PREFIX>-YYYYMMddkkss
```
and a Code 128 Full ASCII barcode as a png image

It is implemented using
Zxing Library
https://github.com/zxing/zxing

Please note this is a sample, it is by no means a complete solution with any security implication

Compile
============================
On project git repo level, type
```
gradle build
```

Run
============================
Execute the jar with
```
java -jar <result jar file>.jar "PMP"
```
PMP stands for series generation
