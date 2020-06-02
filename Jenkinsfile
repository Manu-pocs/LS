pipeline {
  agent {
    node {
      label 'master'
    }

  }
  stages {
    stage('Build') {
      steps {
        tool 'gradle-4.6'
        tool 'JDK8'
        bat 'gradlew.bat clean assemble'
        archiveArtifacts '**/*.jar'
      }
    }

  }
  environment {
    GRADLE_HOME = 'C:\\Users\\Mitesh\\.gradle\\wrapper\\dists\\gradle-4.6-all\\bcst21l2brirad8k2ben1letg\\gradle-4.6'
  }
}