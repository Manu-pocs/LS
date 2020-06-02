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
        bat 'C:\\Users\\Mitesh\\.gradle\\wrapper\\dists\\gradle-4.6-all\\bcst21l2brirad8k2ben1letg\\gradle-4.6\\bin\\gradle.bat -b portal\\7.2\\java8\\workspace-gradle\\build.gradle clean assemble'
        bat 'C:\\Users\\Mitesh\\.gradle\\wrapper\\dists\\gradle-4.6-all\\bcst21l2brirad8k2ben1letg\\gradle-4.6\\bin\\gradle.bat tasks'
        archiveArtifacts '**/libs/*.jar'
      }
    }

  }
  environment {
    ANDROID_HOME = 'C:\\Program Files (x86)\\Android\\android-sdk'
    JAVA_HOME = 'C:\\Program Files\\Java\\jdk1.8.0_111'
  }
}