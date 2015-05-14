r20 = 47
print 'r20:', bin(r20)

r18 = r20
r18 = (r18 >> 4)
print 'r18', bin(r18)

r19 = r20
r19 = r19 & 0b1111
print 'r19', bin(r19)

r18 *= 10
print 'r18', bin(r18)

r18 += r19
print 'r18', bin(r18)