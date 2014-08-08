require 'socket'
server = TCPServer.open(2000)
client = server.accept
for i in 0..5
  ii = 5 - i
  client.puts "countdown - #{ii}"
  sleep 0.5
end
text = "GO Loops in Ruby are used to execute the same block of code a specified number of times. This chapter details all the loop statements supported by Ruby. Executes code while conditional is true. A while loop's conditional is separated from code by the reserved word do, a newline, backslash \, or a semicolon ;."
puts text
client.puts text
puts "waiting for text from client:"
loop do
  line = client.gets
  if match = line.match(/connect\s(.*)/)
    client_name = match.captures
    puts "#{client_name} connected!"
  else
    STDOUT.write "#{line.chomp} (code #{line[0].ord})"
  end
end
puts "closing..."
client.close
