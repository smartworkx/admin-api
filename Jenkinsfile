#!groovy

node {
    // Mark the code checkout 'stage'....
    stage 'Stage Checkout'

    // Checkout code from repository and update any submodules
    checkout scm
    // sh 'git submodule update --init'

    stage 'Stage Build'

    //build your gradle flavor, passes the current build number as a parameter to gradle
    sh "gradle clean assemble"

    stage 'Test'
    ansiblePlaybook(
            playbook: 'provisioning/db-servers.yml',
            inventory: 'provisioning/ci.inventory ',
            extras: '-u admin',
            credentialsId: 'bec43108-1819-465a-bf56-91324f852fc1'
    )
    sh "gradle test -PjvmArgs='-Dspring.profiles.active=ci'"

    stage 'Stage Archive'
    step([$class: 'ArtifactArchiver', artifacts: '**/build/libs/*.jar', fingerprint: true])
    sh 'echo Publishing doc'
}

stage 'Deploy test'
timeout(time: 1, unit: 'DAYS') {
    input 'Do you want to install on test?'
}

node {
    sh 'echo deploying on test'
    ansiblePlaybook(
            playbook: 'provisioning/db-servers.yml',
            inventory: 'provisioning/test.inventory ',
            extras: '-u admin',
            credentialsId: 'bec43108-1819-465a-bf56-91324f852fc1'
    )
    ansiblePlaybook(
            playbook: 'provisioning/api-servers.yml',
            inventory: 'provisioning/test.inventory ',
            extras: '-u admin',
            credentialsId: 'bec43108-1819-465a-bf56-91324f852fc1'
    )
}

stage 'Deploy prod'
timeout(time: 1, unit: 'DAYS') {
    input 'Do you want to install on prod?'
}

node {
    sh 'echo deploying on prod'
    ansiblePlaybook(
            playbook: 'provisioning/db-servers.yml',
            inventory: 'provisioning/prod.inventory ',
            extras: '-u admin',
            credentialsId: 'bec43108-1819-465a-bf56-91324f852fc1'
    )
    ansiblePlaybook(
            playbook: 'provisioning/api-servers.yml',
            inventory: 'provisioning/prod.inventory ',
            extras: '-u admin',
            credentialsId: 'bec43108-1819-465a-bf56-91324f852fc1'
    )
}
