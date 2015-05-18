#Some Code!
How to temporarely stop ISR.

```c
/******* Test on switch 1 ***********/		
	if ((~PINA) & (1 << 1) )        //   then test on switch 1
	{
		if(tid_1.updated == 0)     
// use the updated field to see if tid_1 is updated on this key press !
		{ 
			ATOMIC_BLOCK(ATOMIC_RESTORESTATE)  
// This macro stores the SREG turns off global IRQ when entering the ATOMIC_BLOCK, 
/and it restore the SREG when exiting the ATOMIC_BLOCK
			{                                  
// The reason for turning off IRQ in this block, 
//We do not want the ISR to update the tid when we read it!!
		       tid_1.sek = tid.sek ;
		       tid_1.min = tid.min ;
		       tid_1.timer = tid.timer ;
			}
		   tid_1.updated = 1;     
// set the updated field to indicate the tim_1 is already updated on this key press
		}
	}
	else
	    tid_1.updated = 0;       
// reset the updated field to indicate that switch 1 is released.

```

#More obs!

![Timer/Counter and some](timercounter.PNG)

![SREG](SREG1.PNG)

![SREG extended](SREG2.PNG)

![Assembler directives](assdirect.PNG)