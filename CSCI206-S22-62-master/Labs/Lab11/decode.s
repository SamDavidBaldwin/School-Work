Dump of assembler code for function main:
   0x0000000000010604 <+0>:	addi	sp,sp,-16
   0x0000000000010606 <+2>:	sd	ra,8(sp)
   0x0000000000010608 <+4>:	sd	s0,0(sp)
   0x000000000001060a <+6>:	addi	s0,sp,16
   0x000000000001060c <+8>:	li	a5,12
   0x000000000001060e <+10>:	li	a4,1039
   0x0000000000010612 <+14>:	li	a3,4
   0x0000000000010614 <+16>:	li	a2,3
   0x0000000000010616 <+18>:	li	a1,2
   0x0000000000010618 <+20>:	li	a0,1
   0x000000000001061a <+22>:	jal	ra,0x104de <test>
   0x000000000001061e <+26>:	lui	a5,0x10
   0x0000000000010620 <+28>:	addi	a0,a5,1920 # 0x10780
   0x0000000000010624 <+32>:	jal	ra,0x10410 <puts@plt>
   0x0000000000010628 <+36>:	lui	a5,0x10
   0x000000000001062a <+38>:	addi	a0,a5,1944 # 0x10798
   0x000000000001062e <+42>:	jal	ra,0x10410 <puts@plt>
   0x0000000000010632 <+46>:	li	a1,4
   0x0000000000010634 <+48>:	lui	a5,0x10
   0x0000000000010636 <+50>:	addi	a0,a5,1968 # 0x107b0
   0x000000000001063a <+54>:	jal	ra,0x10420 <printf@plt>
   0x000000000001063e <+58>:	li	a1,8
   0x0000000000010640 <+60>:	lui	a5,0x10
   0x0000000000010642 <+62>:	addi	a0,a5,2008 # 0x107d8
   0x0000000000010646 <+66>:	jal	ra,0x10420 <printf@plt>
   0x000000000001064a <+70>:	addi	a1,gp,-1976
   0x000000000001064e <+74>:	lui	a5,0x11
   0x0000000000010650 <+76>:	addi	a0,a5,-2048 # 0x10800
   0x0000000000010654 <+80>:	jal	ra,0x10420 <printf@plt>
   0x0000000000010658 <+84>:	li	a5,0
   0x000000000001065a <+86>:	mv	a0,a5
   0x000000000001065c <+88>:	ld	ra,8(sp)
   0x000000000001065e <+90>:	ld	s0,0(sp)
   0x0000000000010660 <+92>:	addi	sp,sp,16
   0x0000000000010662 <+94>:	ret
End of assembler dump.

