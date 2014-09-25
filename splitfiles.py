def files():
    n = 0
    while True:
        n += 1
        yield open('/output/dir/%d.part' % n, 'w')


pat = '<?xml'
fs = files()
outfile = next(fs) 

with open(filename) as infile:
    for line in infile:
        if pat not in line:
            outfile.write(line)
        else:
            items = line.split(pat)
            outfile.write(items[0])
            for item in items[1:]:
                outfile = next(fs)
                outfile.write(pat + item)