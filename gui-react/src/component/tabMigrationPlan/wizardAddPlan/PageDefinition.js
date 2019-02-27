import React, { Component } from 'react';
import axios from 'axios';

import { Button } from "patternfly-react";

import PageDefinitionSearchTable from "./PageDefinitionSearchTable";
import { Mockup_processMapping_Info } from '../../common/MockupData';
import {BACKEND_URL, USE_MOCK_DATA} from '../../common/PimConstants';

export default class PageDefinition extends Component {
    constructor (props) {
      super(props);

      this.state = {
          sourceProcessId: '',
          sourceGroupId: '',
          sourceArtifactId: '',
          sourceVersion: '',
          targetProcessId: '',
          targetGroupId: '',
          targetArtifactId: '',
          targetVersion: ''
      };
    }

    //This is used in add plan wizard (for edit plan) to load the inital data to form fields
    componentDidMount(){

        const sourceVersion = this.props.initSourceContainerId.substring((this.props.initSourceContainerId.indexOf('_') + 1),this.props.initSourceContainerId.length);
        const targetVersion = this.props.initTargetContainerId.substring((this.props.initTargetContainerId.indexOf('_') + 1),this.props.initTargetContainerId.length);

        this.setState({
            sourceProcessId: this.props.initProcessId,
            sourceGroupId: this.props.initProcessId,
            sourceArtifactId: this.props.initProcessId,
            sourceVersion: sourceVersion,
            targetProcessId: this.props.initProcessId,
            targetGroupId: this.props.initProcessId,
            targetArtifactId: this.props.initProcessId,
            targetVersion: targetVersion
        });
    }

    //this function is for helping test only
    fillWithTestRecord = () =>{
        this.setState({
            sourceProcessId: 'evaluation',
            sourceGroupId: 'evaluation',
            sourceArtifactId: 'evaluation',
            sourceVersion: '2.0.0-SNAPSHOT',
            targetProcessId: 'Mortgage_Process.MortgageApprovalProcess',
            targetGroupId: 'mortgage-process',
            targetArtifactId: 'mortgage-process',
            targetVersion: '1.0.0-SNAPSHOT'
        });
    }

    copySourceToTarget = () =>{
        this.setState({
            targetProcessId:  this.state.sourceProcessId,
            targetGroupId: this.state.sourceGroupId,
            targetArtifactId: this.state.sourceArtifactId,
            targetVersion: this.state.sourceVersion
        });
    }

    handleSourceProcessIdChange = (value) =>{
        this.setState({sourceProcessId: value});
    }

    handleSourceGroupIdChange = (value) =>{
        this.setState({sourceGroupId: value});
    }

    handleSourceArtifactIdChange = (value) =>{
        this.setState({sourceArtifactId: value});
    }

    handleSourceVersionChange = (value) =>{
        this.setState({sourceVersion: value});
    }

    handleTargetProcessIdChange = (value) =>{
        this.setState({targetProcessId: value});
    }

    handleTargetGroupIdChange = (value) =>{
        this.setState({targetGroupId: value});
    }

    handleTargetArtifactIdChange = (value) =>{
        this.setState({targetArtifactId: value});
    }

    handleTargetVersionChange = (value) =>{
        this.setState({targetVersion: value});
    }

    retriveBothInfo = () =>{
        //console.log('this.state.sourceProcessId ' + this.state.sourceProcessId);
        //console.log('this.state.targetProcessId ' + this.state.targetProcessId);
        if (USE_MOCK_DATA){
            console.log('retriveBothInfo use mock data ');
            const mockData = Mockup_processMapping_Info;
            this.props.setInfo(mockData.sourceInfo,mockData.targetInfo);

            var input = document.getElementById("hiddenField_sourceContainerId");
            var containerId = this.state.sourceProcessId + "_" + this.state.sourceVersion;
            var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, "value").set;
            nativeInputValueSetter.call(input, containerId);
            //once fired the event, this currentInputValue will be saved in the wizard form's values
            var ev = new Event('input', { bubbles: true});
            input.dispatchEvent(ev);

            var input = document.getElementById("hiddenField_targetContainerId");
            var containerId = this.state.targetProcessId + "_" + this.state.targetVersion;
            var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, "value").set;
            nativeInputValueSetter.call(input, containerId);
            //once fired the event, this currentInputValue will be saved in the wizard form's values
            var ev = new Event('input', { bubbles: true});
            input.dispatchEvent(ev);


            input = document.getElementById("hiddenField_targetProcessId");
            var processId = this.state.targetProcessId;
            var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, "value").set;
            nativeInputValueSetter.call(input, processId);
            //once fired the event, this currentInputValue will be saved in the wizard form's values
            var ev = new Event('input', { bubbles: true});
            input.dispatchEvent(ev);

        }else{
            const servicesUrl = BACKEND_URL + "/both";
            axios.get(servicesUrl, {
                params: {
                    sourceProcessId: this.state.sourceProcessId,
                    sourceGroupId: this.state.sourceGroupId,
                    sourceArtifactId: this.state.sourceArtifactId,
                    sourceVersion: this.state.sourceVersion,
                    targetProcessId: this.state.targetProcessId,
                    targetGroupId: this.state.targetGroupId,
                    targetArtifactId: this.state.targetArtifactId,
                    targetVersion: this.state.targetVersion
                }
            }).then (res => {
                //console.log('retriveBothInfo response: ' + JSON.stringify(res.data, null, 2) );

                this.props.setInfo(res.data.sourceInfo,res.data.targetInfo);

                var input = document.getElementById("hiddenField_sourceContainerId");
                var containerId = this.state.sourceProcessId + "_" + this.state.sourceVersion;
                var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, "value").set;
                nativeInputValueSetter.call(input, containerId);
                //once fired the event, this currentInputValue will be saved in the wizard form's values
                var ev = new Event('input', { bubbles: true});
                input.dispatchEvent(ev);

                var input = document.getElementById("hiddenField_targetContainerId");
                var containerId = this.state.targetProcessId + "_" + this.state.targetVersion;
                var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, "value").set;
                nativeInputValueSetter.call(input, containerId);
                //once fired the event, this currentInputValue will be saved in the wizard form's values
                var ev = new Event('input', { bubbles: true});
                input.dispatchEvent(ev);


                input = document.getElementById("hiddenField_targetProcessId");
                var processId = this.state.targetProcessId;
                var nativeInputValueSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, "value").set;
                nativeInputValueSetter.call(input, processId);
                //once fired the event, this currentInputValue will be saved in the wizard form's values
                var ev = new Event('input', { bubbles: true});
                input.dispatchEvent(ev);


            });

        }



    }


  render() {

    return (
      <div className="form-horizontal">
                    <Button onClick={this.fillWithTestRecord}>quick fill for testing</Button>

                    <PageDefinitionSearchTable tableHeader="Source "
                        processId={this.state.sourceProcessId}
                        groupId={this.state.sourceGroupId}
                        artifactId={this.state.sourceArtifactId}
                        version={this.state.sourceVersion}

                        handleProcessIdChange={this.handleSourceProcessIdChange}
                        handleGroupIdChange={this.handleSourceGroupIdChange}
                        handleArtifactIdChange={this.handleSourceArtifactIdChange}
                        handleVersionChange={this.handleSourceVersionChange}

                        initContainerId={this.props.initSourceContainerId}
                        initProcessId={this.props.initProcessId}
                    />

                    <Button onClick={this.copySourceToTarget}> Copy Source To Target</Button>

                    <PageDefinitionSearchTable tableHeader="Target "
                        processId={this.state.targetProcessId}
                        groupId={this.state.targetGroupId}
                        artifactId={this.state.targetArtifactId}
                        version={this.state.targetVersion}

                        handleProcessIdChange={this.handleTargetProcessIdChange}
                        handleGroupIdChange={this.handleTargetGroupIdChange}
                        handleArtifactIdChange={this.handleTargetArtifactIdChange}
                        handleVersionChange={this.handleTargetVersionChange}

                        initContainerId={this.props.initTargetContainerId}
                        initProcessId={this.props.initProcessId}
                    />


                    <Button bsStyle="default" onClick={() => this.retriveBothInfo()}>Retrive definition from backend</Button>

                    <div className="form-group">
                        <label className="col-sm-2 control-label">{this.props.sourceInfo.containerId}</label>
                        <label className="col-sm-2 control-label">{this.props.targetInfo.containerId}</label>
                    </div>
                    <div style={{display: 'none'}}>
                        <input type="text" className="form-control" name="sourceContainerId" id="hiddenField_sourceContainerId" />
                        <input type="text" className="form-control" name="targetContainerId" id="hiddenField_targetContainerId" />
                        <input type="text" className="form-control" name="targetProcessId" id="hiddenField_targetProcessId" />
                    </div>


      </div>
    );
  }
}
