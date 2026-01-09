pipeline {


    agent any

    stages {

        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Compile') {
            steps {
                sh 'mvn clean compile'
            }
        }

        stage('Unit Tests') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package -DskipTests'
            }
        }

       stage('Build') {
            steps {
                echo 'build'
                sh 'docker-compose up -d --build'
                echo 'application is running'
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
