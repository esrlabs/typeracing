require 'socket'
require 'io/console'

hostname = "localhost"
port = 2000
username="oliver_and_frauke"
s = TCPSocket.open(hostname,port)
s.puts "connect #{username}"

done = false
while (line = s.gets)
  if match = line.match(/countdown\s(.*)/)
    countdown = match.captures
    puts "was countdown: #{countdown}"
  elsif match = line.match(/GO\s(.*)/)
    text = match.captures
    done = true
  end
  break if done
end
puts "starting: please type this: #{text}"

while c = STDIN.getch
  STDOUT.write c
  s.puts c
  s.flush
end
s.close
