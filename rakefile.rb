require 'rake/clean'

namespace :server do
  desc "start server"
  task :start do
    sh "ruby client/server.rb"
  end
end
namespace :client do
  desc "start client"
  task :start do
    sh "ruby client/client.rb"
  end
end
