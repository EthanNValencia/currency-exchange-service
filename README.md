How to run an additional instance on a different port with Eclipse: 
1. Green run -> drop down
2. Run Configurations...
3. Rename application and add _Port<portnum> to the end of it.
4. Duplicate application and add _Port<portnum> to the end of it.
5. Add -Dserver.port=<portnum> to the vm arguments. 
6. Run both applications. 