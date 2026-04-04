pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK17'
    }

    parameters {
        string(name: 'ENVIRONMENT', defaultValue: 'dev', description: 'Test environment')
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master',
                    credentialsId: 'github-credentials',
                    url: 'https://github.com/luarsabiko/jenkins-implementation'
            }
        }

        stage('Clean') {
            steps {
                dir('final-task') {
                    bat 'mvn clean'
                }
            }
        }

        stage('Test - Parallel Browsers') {
            parallel {
                stage('Chrome') {
                    steps {
                        dir('final-task') {
                            bat 'mvn test -Dbrowser=chrome -Denvironment=%ENVIRONMENT% -Dheadless=true'
                        }
                    }
                    post {
                        always {
                            junit 'final-task/target/surefire-reports/**/*.xml'
                        }
                    }
                }
                stage('Firefox') {
                    steps {
                        dir('final-task') {
                            bat 'mvn test -Dbrowser=firefox -Denvironment=%ENVIRONMENT% -Dheadless=true'
                        }
                    }
                    post {
                        always {
                            junit 'final-task/target/surefire-reports/**/*.xml'
                        }
                    }
                }
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    includeProperties: true,
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'final-task/target/allure-results']]
                ])
            }
        }
    }

    post {
        failure {
            echo 'Tests failed'
        }
    }
}