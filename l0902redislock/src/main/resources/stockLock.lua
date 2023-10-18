local realLockValue = redis.call('get',KEYS[1])
if(realLockValue==ARGV[1])
then redis.call('del',KEYS[1])
end