def cloud_finder(file):
    for line in file:
        #if 'distributed system' in line or 'Client/server' in line or 'Distributed applications' in line or 'Distributed databases' in line or 'Network operating systems' in line: # or 'cloud architecture' in line: # or 'distributed system' in line or 'Distributed system' in line or 'cdn' in line or 'cloud operating' in line or 'cloud storage' in line:
        if 'cloud computing' in line or 'cloud architecture' in line or 'cloud architecture' in line or 'cdn' in line or 'cloud operating' in line or 'cloud storage' in line or 'cloud storage' in line or 'cloudstorming' in line or 'cloudsourcing' in line: 
            destination.write(line)


source = open('dblpdata.txt','rb')
destination = open('cloud2.txt','w+')

cloud_finder(source)