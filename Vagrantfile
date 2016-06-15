# -*- mode: ruby -*-
# vi: set ft=ruby :

# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure(2) do |config|

config.vm.define "api" do |api|
    api.vm.box = "ubuntu/trusty64"
    api.vm.network "forwarded_port", guest: 8080, host: 5081
    api.vm.network "forwarded_port", guest: 22, host: 2223
    api.vm.network "private_network", ip: "192.168.33.11"
    api.vm.provision "ansible" do |ansible|
        ansible.playbook = "provisioning/api-servers.yml"
    end
  end

  config.vm.define "db" do |db|
    db.vm.box = "ubuntu/trusty64"
    db.vm.network "private_network", ip: "192.168.33.12"
    db.vm.provision "ansible" do |ansible|
        ansible.playbook = "provisioning/db-servers.yml"
    end
  end

end
