#Time in C
```c

#include <avr/io.h>
#include <avr/interrupt.h>

#define F_CPU 4000000
#define tick = F_CPU/64

type_tid sw = {0,0}; //allokerer plass til allokeringen trenger 2 bytes, de settes til 0
type_tid* tider_p[256]; 
//lager en array; allokerer 510 bytes (adresseområde) - 0 området tells ikke ergo 256
uint8_t trykk = 0;
uint8_t antallTider = 0;

static void initTimer(void)
{
	OCR1A = tick; //output compare med tick
	TCCR1B = (1 << cs10)|(1 << cs11); //sett prescaler til 64
	TCNT1 = 0; //setter timer counter til 0
	TIMSK = (1 << OCIE1A); //interupt on compare (OCR1A)
	sei(); //start ISR routines
}

static void initPort(void)
{
	DDRD = ~0x04; //setter PORTD bit 4 til inngang (0)
}

ISR(TIMER1_COMPA_vector)
{
	incTid(&sw); //& finner adresse til sw og caller incTid med den adressen til sw
}

static void incTid(type_tid* tid_p) 
//inc tid tar imot en peker av type_tid og i sin scope refferer til den som navnet: tid_p
{
	if (tid_p->sekunder < 59) //sekunder skal økes til den er 58
		tid_p->sekunder++;
	else //når den er blitt 59 vil den nå nulle og øke minutter
	{
		tid_p->sekunder=0;
		tid_p->min++;
	}

	//alternativ løsning med modulus:
	if ++ tid_p->sekunder % 60 == 0 
	//øker sekunder, også sjekker om sekunder modulus 60 blir 0
	{
		tid_p->sekunder = 0;
		tid_p->minutter++;
	}
}

static int main(void)
{
	initPort();
	initTimer();

	while(antallTider < 255) //kjør til listen blir full
	{
		if(~PIND & 0x04) //hvis PIND er trykt (0)
		{
			if(!trykk) //hvis trykk variabelen == 0
			{
				type_tid* nyTid = (type_tid*)malloc(sizeof(type_tid)); 
				//caster type (type_tid*), malloc reserverer minne samme størrelse som type_tid
				nyTid->sekunder = sw.sekunder; //lagrer fra sw sekunder til nyTid sekunder
				nyTid->minutter = sw.minutter; //lagrer fra sw minutter til nyTid minutter
				tider_p[antallTider++]=nyTid; //lagrer nyTid var. til antallTider array
				trykk = 1;
			}
			else
				trykk = 0;
		}
	}
	return 0;
}

```