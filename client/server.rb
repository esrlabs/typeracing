require 'socket'
server = TCPServer.open(2000)
client = server.accept
loop do
  t = client.gets
  puts "C: #{t}"
end
client.close
