#define F_CPU 4000000
#define tickA F_CPU/64
#define tickB tickA/2

static void initTimers(void);
static void initPort(void);
static void flash(void)

static void initPort(void)
{
	DDRA = 0xFF;
	DDRB = 0xFF;
	PORTA = 0x0;
	PORTB = 0x0;
	DDRC = ~0x01;
	PORTC = 0x00;
}

static void initTimers(void)
{
	//Timer 1
	TCNT1 = 0x0;
	TCCR1B = (1 << CS10)|(1 << CS11)|(1 << WGM12);
	OCR1A = tickA;
	TIMSK = (1 << OCIE1A);

	//Timer 2
	TCNT3 = 0x0;
	TCCR3B = (1 << CS10)|(1 << CS11)|(1 << WGM12);
	OCR3A = tickB;
	ETIMSK = (1 << OCR3A);

	sei();
}

ISR(TIMER1_COMPA_vect)
{
	counter_A++;
}

ISR(TIMER3_COMPA_vect)
{
	counter_B++;
}

static int main(void)
{
	initPort();
	initTimers();
	static volatile uint8_t counter_A;
	static update = 0;

	while(1)
	{
		while (~PINC & 1)
		{
			if (!update) {
				cli();
				PORTA = 0xFF;
				PORTB = 0xFF;
				update = 1;
			}
		}

		if (update)
		{
			update = 0;
			sei();
		}

		if (counter_A > 6)
			counter_A = 0;
		PORTA = (1 << counter++);

		if (counter_B > 6)
			counter_B = 0;
		PORTB = (1 << counter++);
	};

	return 0;
}