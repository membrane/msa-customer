pipeline {
    agent any
    stages {
        stage('Build') {
            agent {
                docker {
                    image 'maven:3.5.3-jdk-8-alpine'
                }
            }
            steps {
                sh 'mvn package'
                stash name: 'jar', includes: 'target/**.jar'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }
        stage('Test') {
            parallel {
                stage('Integration test') {
                    steps {
                        unstash 'jar'
                        echo 'Running integration tests'
                    }
                }
                stage('Functional test') {
                    steps {
                        echo 'Running functional tests'
                    }
                }
            }
        }
        stage('Deploy to stage') {
            steps {
                echo 'Deploying to stage'
            }
        }
        stage('Deploy to prod') {
            steps {
                echo 'Deploying to prod'
            }
        }
    }
}
