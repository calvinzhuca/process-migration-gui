mvn clean package
rm -rf /home/czhu/jboss/RHPAM/rhpam-7.3/rhpam-businessCentral_73/standalone/deployments/pim-gui-backend.*
cp /home/czhu/works/git/bpms7_git/calvin_fork/process-migration-gui/gui-backend/target/pim-gui-backend.war /home/czhu/jboss/RHPAM/rhpam-7.3/rhpam-businessCentral_73/standalone/deployments/
touch /home/czhu/jboss/RHPAM/rhpam-7.3/rhpam-businessCentral_73/standalone/deployments/pim-gui-backend.war.dodeploy
ls -l /home/czhu/jboss/RHPAM/rhpam-7.3/rhpam-businessCentral_73/standalone/deployments/

