pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'yusufbugrakilic1907/swe304-project4:latest'
        KUBECONFIG = 'C:/JenkinsKube/config'
    }

    stages {
        stage('Stage 1 - Clone Project from GitHub') {
            steps {
                checkout scm
            }
        }

        stage('Stage 2 - Build Jar File') {
            steps {
                bat 'mvnw.cmd clean package'
            }
        }

        stage('Stage 3 - Create Docker Image') {
            steps {
                bat 'docker build -t %DOCKER_IMAGE% .'
            }
        }

        stage('Stage 4 - Login to DockerHub') {
            steps {
                withCredentials([usernamePassword(
                    credentialsId: 'dockerhub-credentials',
                    usernameVariable: 'DOCKERHUB_USERNAME',
                    passwordVariable: 'DOCKERHUB_PASSWORD'
                )]) {
                    bat 'echo %DOCKERHUB_PASSWORD%| docker login -u %DOCKERHUB_USERNAME% --password-stdin'
                }
            }
        }

        stage('Stage 5 - Push Docker Image to DockerHub') {
            steps {
                bat 'docker push %DOCKER_IMAGE%'
            }
        }

        stage('Stage 6 - Deploy to Kubernetes') {
            steps {
                bat 'kubectl apply -f k8s\\deployment.yaml'
                bat 'kubectl apply -f k8s\\service.yaml'
                bat 'kubectl rollout restart deployment/swe304-project4-deployment'
                bat 'kubectl rollout status deployment/swe304-project4-deployment'
                bat 'kubectl get pods'
                bat 'kubectl get svc'
            }
        }
    }
}