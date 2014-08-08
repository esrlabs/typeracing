require 'socket'
require 'io/console'

# hostname = "localhost"
hostname = "172.31.97.152"
port = 2000
username="oliver_and_frauke"
puts "trying to connect to server (#{hostname}:#{port})"
s = TCPSocket.open(hostname,port)
puts "connected to server"
s.puts "connect #{username}"
s.flush

done = false
while (line = s.gets)
  if match = line.match(/countdown\s(.*)/)
    countdown = match.captures
    puts "was countdown: #{countdown}"
  elsif match = line.match(/go\s(.*)/)
    text = match.captures
    done = true
  end
  break if done
end
puts "starting: please type this: #{text}"

while c = STDIN.getch
  STDOUT.write c
  s.write c
  s.flush
end
s.close
