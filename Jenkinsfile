pipeline {
  agent {
    node {
      label 'master'
    }

  }
  stages {
    stage('Build') {
      steps {
        tool 'gradle-5.4.1-all'
        tool 'JDK8'
        bat 'gradlew.bat clean assemble'
        archiveArtifacts '**/*.jar'
      }
    }

    stage('Refresh') {
      steps {
        writeFile(file: '.refresh', text: 'refresh')
      }
    }

  }
  environment {
    JENKINS_NODE_COOKIE = 'dontKillMe'
  }
}