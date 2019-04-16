#!/bin/bash
echo 'start update, upgrade, installs (internet needed) script'
sudo apt-get update
sudo apt-get upgrade -y
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password password '
sudo debconf-set-selections <<< 'mysql-server mysql-server/root_password_again password '
sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/reconfigure-webserver multiselect apache2"
sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/dbconfig-install boolean true"
sudo debconf-set-selections <<< "phpmyadmin phpmyadmin/app-password-confirm password "
time sudo apt-get install \
vim vim-gtk \
automake \
codeblocks \
arduino \
clamav \
filezilla \
git-core \
gparted \
xorg xorg-dev \
xscreensaver \
apache2 \
mysql-server mysql-client \
php php-imap \
php-mysql php-curl php-gd php-json php-mcrypt php-opcache php-xmlrpc \
libapache2-mod-php \
libjpeg-dev \
libx11-dev libxpm-dev \
libxp-dev libxmu-dev \
libfreeimage-dev libopenal-dev libpango1.0-dev \
libsndfile1-dev libtiff5-dev libwebp-dev libturbojpeg-dev fbi \
libudev-dev libasound2-dev -y
wget http://www.genlogic.com/download/glg-CE-3-6-linux-pi-arm6.tar.gz \
-O /usr/local/glg/glg-3-6-linux-pi-arm6.tar.gz
gunzip /usr/local/glg/glg-3-6-linux-pi-arm6.tar.gz 
sudo tar zxvf glg-3-6-linux-pi-arm6.tar --directory /usr/local
wget https://download.zerotier.com/debian/stretch/pool/main/z/zerotier-one/zerotier-one_1.2.12_armhf.deb
sudo dpkg --install zerotier-one_1.2.12_armhf.deb
echo finished script