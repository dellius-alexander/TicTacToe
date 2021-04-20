pipeline {
  agent any
  stages {
    stage('') {
      steps {
        dockerNode(
          image: 'registry.dellius.app/oracle/jdk:11 ',
          credentialsId: 'PRIVATE_CNTR_REGISTRY', 
          dockerHost: 'unix://var/run/docker.sock')
      }
    }

  }
}