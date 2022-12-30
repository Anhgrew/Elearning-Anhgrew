# adding repository and installing nginx		
apt update
apt install nginx -y
cat <<EOT > elearning
upstream elearning {

 server app01:8080;

}

server {

  listen 80;

location / {

  proxy_pass http://elearning;

}

}

EOT

mv elearning /etc/nginx/sites-available/elearning
rm -rf /etc/nginx/sites-enabled/default
ln -s /etc/nginx/sites-available/elearning /etc/nginx/sites-enabled/elearning

#starting nginx service and firewall
systemctl start nginx
systemctl enable nginx
systemctl restart nginx

echo "Done..."
