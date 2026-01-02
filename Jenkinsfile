pipeline {
    agent {
        label 'docker'
    }

    tools {
        maven 'Maven3'
        jdk 'JDK17'
    }

    environment {
        APP_NAME = userapp'
        NEXUS_REGISTRY = 'nexus.mycompany.com:8083'
        IMAGE_TAG = "${env.BUILD_NUMBER}"
    }

    options {
        timestamps()
        disableConcurrentBuilds()
        buildDiscarder(logRotator(numToKeepStr: '10'))
    }

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

        stage('Docker Build') {
            when {
                branch 'main'
            }
            steps {
                sh """
                  docker build -t ${NEXUS_REGISTRY}/${APP_NAME}:${IMAGE_TAG} .
                """
            }
        }

        stage('Docker Push') {
            when {
                branch 'main'
            }
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'nexus-docker-creds',
                    usernameVariable: 'DOCKER_USER',
                    passwordVariable: 'DOCKER_PASS'
                )]) {
                    sh """
                      docker login ${NEXUS_REGISTRY} -u $DOCKER_USER -p $DOCKER_PASS
                      docker push ${NEXUS_REGISTRY}/${APP_NAME}:${IMAGE_TAG}
                    """
                }
            }
        }
    }

    post {
        success {
            echo 'CI Pipeline completed successfully'
        }

        failure {
            echo 'CI Pipeline failed'
        }

        always {
            cleanWs()
        }
    }
}
