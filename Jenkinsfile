pipeline {


    agent any

  	options {
        timeout(time: 45, unit: 'MINUTES')   // whole job protection
    }

    stages {

        stage('Checkout') {
            steps {
                checkout([
                    $class: 'GitSCM',
                    branches: [[name: '*/master']],
                    userRemoteConfigs: [[url: 'https://github.com/arunjat11/Userapp.git']],
                    extensions: [
                        [$class: 'CloneOption', depth: 1, noTags: true, shallow: true, timeout: 30]
                     ]
                    ])
            }
        }

        stage('Build') {
        		agent {
        			docker {
           				 image 'maven:3.9.6-eclipse-temurin-17'
       				 }
    			}
            steps {
                 sh 'mvn clean package -DskipTests'
            }
        }

 		stage('Docker Build') {
    		steps {
        		sh 'docker build -t userapp:latest .'
    		}
		}
		
		stage('Deploy') {
   		 	steps {
        		sh 'docker-compose up -d --build'
    		}
		}
	}

    post {
        success {
            echo 'CICD Pipeline completed successfully'
        }

        failure {
            echo 'CICD Pipeline failed'
        }

        always {
            cleanWs()
        }
    }
}
