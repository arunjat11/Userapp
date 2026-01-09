pipeline {


    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
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
        		sh 'docker compose up -d --build'
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
