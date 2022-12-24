Vagrant.configure("2") do |config|
  config.hostmanager.enabled = true 
  config.hostmanager.manage_host = true
  
### DB vm  ####
  config.vm.define "db01" do |db01|
    db01.vm.box = "geerlingguy/centos7"
    db01.vm.hostname = "db01"
    db01.vm.network "private_network", ip: "192.168.56.15"
    db01.vm.provision "shell", path: "mysql.sh"  

  end
  
### tomcat vm ###
   config.vm.define "app01" do |app01|
    app01.vm.box = "geerlingguy/centos7"
    app01.vm.hostname = "app01"
    app01.vm.network "private_network", ip: "192.168.56.12"
    app01.vm.provision "shell", path: "tomcat.sh"  
    app01.vm.provider "virtualbox" do |vb|
     vb.memory = "1024"
    end
   end
   
  
### Nginx VM ###
  config.vm.define "web01" do |web01|
    web01.vm.box = "ubuntu/xenial64"
    web01.vm.hostname = "web01"
    web01.vm.network "private_network", ip: "192.168.56.11"
    web01.vm.provision "shell", path: "nginx.sh"  
end
  
end
