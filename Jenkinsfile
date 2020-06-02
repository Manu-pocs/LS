pipeline {
  agent {
    node {
      label 'master'
    }

  }
  stages {
    stage('Static Code Analysis') {
      steps {
        bat 'echo "SCA"'
        bat 'F:\\1.DevOps\\2020\\sonar-scanner-3.2.0.1227-windows\\bin\\sonar-scanner.bat -Dsonar.host.url=http://localhost:9000/ -Dsonar.login=cba67104bb4f4ef081b55e7ef43168a40b49d6b9 -Dsonar.projectVersion=1.0 -Dsonar.projectKey=liferay-sample -Dsonar.sources=portal\\7.2\\java8\\code\\modules\\applications\\portlets -Dsonar.java.binaries=. '
      }
    }

    stage('Unit Tests') {
      steps {
        echo 'Using JUnit'
      }
    }

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